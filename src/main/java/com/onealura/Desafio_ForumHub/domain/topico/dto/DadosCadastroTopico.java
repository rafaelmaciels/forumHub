package com.onealura.Desafio_ForumHub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
                @NotBlank String titulo,
                @NotBlank String mensagem,
                @NotNull Long cursoId) {
}