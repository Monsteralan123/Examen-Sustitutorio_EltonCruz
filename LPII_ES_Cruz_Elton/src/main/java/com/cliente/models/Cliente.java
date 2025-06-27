package com.cliente.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	

    @Id
    @Column(name = "cod_cliente", length = 6)
    @Pattern(regexp = "^CLI[0-9]{3}$", message = "El código debe tener el formato CLI000")
    private String codCliente;

    @Column(name = "ape_cliente", nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede exceder 50 caracteres")
    private String apeCliente;

    @Column(name = "nom_cliente", nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    private String nomCliente;

    @Column(name = "fecha_nac", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNac;

    @Column(name = "dni", nullable = false, length = 8)
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos numéricos")
    private String dni;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_cli", nullable = false)
    @NotNull(message = "Debe seleccionar un tipo de cliente")
    private TipoCliente tipoCliente;
    
}
