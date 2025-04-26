package challenge.tickets.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	
	private Long id;
	
	@NotNull(message = "El apellido es obligatorio")
	private String apellido;

	@NotNull(message = "El nombre es obligatorio")
	private String nombre;

	@NotNull(message = "El DNI es obligatorio")
	private String dni;

	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate nacimiento;
}
