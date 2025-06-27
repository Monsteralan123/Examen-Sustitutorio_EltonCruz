package com.cliente.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "tb_tipo_cli")
public class TipoCliente {
	
	@Id
	@Column(name="id_tipo_cli")
	private Integer idTipo;
	
	@Column(name="des_tipo_cli")
	private String descripcion;

}
