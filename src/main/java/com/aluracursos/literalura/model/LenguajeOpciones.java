package com.aluracursos.literalura.model;

public enum LenguajeOpciones {

    ENGLISH("en", "inglés"),
    GERMAN("de", "alemán"),
    FRENCH("fr", "francés"),
    SPANISH("es", "español"),
    ITALIAN("it", "italiano"),
    RUSSIAN("ru", "ruso"),
    CHINESE("zh", "chino"),
    PORTUGUES("pt", "portugués");

    private String languageCode;
    private String languageName;

    LenguajeOpciones(String languageCode, String languageSpanish){
        this.languageCode = languageCode;
        this.languageName = languageSpanish;
    }

    public static LenguajeOpciones getNameByCode(String code) {
        for (LenguajeOpciones lenguaje: LenguajeOpciones.values()){
            if (lenguaje.languageCode.equalsIgnoreCase(code)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("No se encontró opción de este idioma: "+ code);
    }


    public static String getSpanishNameByCode(String enumName){
        try {
            LenguajeOpciones lenguaje = LenguajeOpciones.valueOf(enumName.toUpperCase());
            return lenguaje.languageName;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No se encontró opción de este idioma: " + enumName, e);
        }
    }





}
