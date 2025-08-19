package com.onealura.Desafio_ForumHub.domain.topico.dto;

import com.onealura.Desafio_ForumHub.domain.topico.StatusTopico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        StatusTopico status) {
}
