package com.example.springbatchexample.dto;
import org.springframework.jdbc.core.JdbcTemplate;

public class Persona {

    private JdbcTemplate jdbcTemplate;
    private String primerNombre;
    private String segundoNombre;
    private String telefono;

    public int getCountPerson() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM PERSON", Integer.class);
    }

    public Persona() {
        super();
    }

    public Persona(String primerNombre, String segundoNombre, String telefono) {
        super();
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.telefono = telefono;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




}