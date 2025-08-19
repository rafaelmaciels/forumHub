package com.onealura.Desafio_ForumHub.domain.topico.dto;

import java.time.LocalDateTime;

import com.onealura.Desafio_ForumHub.domain.topico.StatusTopico;
import com.onealura.Desafio_ForumHub.domain.topico.Topico;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(), 
                topico.getCurso().getNome()); 
    }
}
