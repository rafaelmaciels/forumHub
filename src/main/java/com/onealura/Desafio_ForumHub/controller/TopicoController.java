package com.onealura.Desafio_ForumHub.controller;

import com.onealura.Desafio_ForumHub.domain.curso.CursoRepository;
import com.onealura.Desafio_ForumHub.domain.topico.Topico;
import com.onealura.Desafio_ForumHub.domain.topico.TopicoRepository;
import com.onealura.Desafio_ForumHub.domain.topico.dto.DadosAtualizacaoTopico;
import com.onealura.Desafio_ForumHub.domain.topico.dto.DadosCadastroTopico;
import com.onealura.Desafio_ForumHub.domain.topico.dto.DadosDetalhamentoTopico;
import com.onealura.Desafio_ForumHub.domain.topico.dto.DadosListagemTopico;
import com.onealura.Desafio_ForumHub.domain.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados,
            @AuthenticationPrincipal Usuario autor, UriComponentsBuilder uriBuilder) {
        // Regra de negócio: não permitir tópicos duplicados
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um tópico com o mesmo título e mensagem.");
        }

        var curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID fornecido."));

        var topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = { "dataCriacao" }) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado."));

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados,
            @AuthenticationPrincipal Usuario autor) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado."));
 
        if (!topico.getAutor().equals(autor)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Acesso negado: você não tem permissão para alterar este tópico.");
        }

        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario autor) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado."));

        if (!topico.getAutor().equals(autor)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Acesso negado: você não tem permissão para excluir este tópico.");
        }

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}