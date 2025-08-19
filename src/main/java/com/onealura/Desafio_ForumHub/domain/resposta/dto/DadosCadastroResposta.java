package com.onealura.Desafio_ForumHub.domain.resposta.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem
) {
}
