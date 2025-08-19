package com.onealura.Desafio_ForumHub.domain.topico.dto;

import com.onealura.Desafio_ForumHub.domain.topico.StatusTopico;
import com.onealura.Desafio_ForumHub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),      // MUDANÇA AQUI: Usando o nome do autor
                topico.getCurso().getNome()       // MUDANÇA AQUI: Usando o nome do objeto Curso
        );
    }
}