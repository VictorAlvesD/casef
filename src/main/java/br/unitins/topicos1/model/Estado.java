package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum Estado {
    ACRE(1, "ACRE"),
    ALAGOAS(2, "ALAGOAS"),
    AMAPA(3, "AMAPA"),
    AMAZONAS(4, "AMAZONAS"),
    BAHIA(5, "BAHIA"),
    CEARA(6, "CEARA"),
    DISTRITO_FEDERAL(7, "DISTRITO_FEDERAL"),
    ESPIRITO_SANTO(8, "ESPIRITO_SANTO"),
    GOIAS(9, "GOIAS"),
    MARANHAO(10, "MARANHAO"),
    MATO_GROSSO(11, "MATO_GROSSO"),
    MATO_GROSSO_DO_SUL(12, "MATO_GROSSO_DO_SUL"),
    MINAS_GERAIS(13, "MINAS_GERAIS"),
    PARA(14, "PARA"),
    PARAIBA(15, "PARAIBA"),
    PARANA(16, "PARANA"),
    PERNAMBUCO(17, "PERNAMBUCO"),
    PIAUI(18, "PIAUI"),
    RIO_DE_JANEIRO(19, "RIO_DE_JANEIRO"),
    RIO_GRANDE_DO_NORTE(20, "RIO_GRANDE_DO_NORTE"),
    RIO_GRANDE_DO_SUL(21, "RIO_GRANDE_DO_SUL"),
    RONDONIA(22, "RONDONIA"),
    RORAIMA(23, "RORAIMA"),
    SANTA_CATARINA(24, "SANTA_CATARINA"),
    SAO_PAULO(25, "SAO_PAULO"),
    SERGIPE(26, "SERGIPE"),
    TOCANTINS(27, "TOCANTINS");

    private int id;
    private String label;

    Estado(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Estado valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for(Estado perfil : Estado.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

    
}

