package challenge.tickets.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
	private Long id;
	
	private Long cliente_id;

    @NotBlank(message = "El nombre del evento es obligatorio")
    private String evento;

    @NotNull(message = "El costo es obligatorio")
    @Positive(message = "El costo debe ser mayor que cero")
    private BigDecimal costo;

    @NotNull(message = "La fecha de vigencia es obligatoria")
    private LocalDate fechaVigencia;
}
