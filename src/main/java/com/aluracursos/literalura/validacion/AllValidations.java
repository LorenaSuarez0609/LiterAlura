package com.aluracursos.literalura.validacion;

import com.aluracursos.literalura.exceptiones.BookNotFoundExceptions;
import com.aluracursos.literalura.exceptiones.InvalidOptionsExceptions;
import com.aluracursos.literalura.model.DatosResultados;

public class AllValidations {
    //Input data validations for the main menu
    public static void verifyMenuInputIsValid(int input) throws Exception {
        if (input < 0 || input > 6) {
            throw new InvalidOptionsExceptions("Opción inválida, intente de nuevo con las opciones disponibles en el menú.");
        }
    }

    // Data validation for Gutendex
    public static void verifyIsnotNullData(DatosResultados data, String bookTitle) throws BookNotFoundExceptions, BookNotFoundExceptions {
        if (data.resultados().isEmpty() || data.resultados() == null){
            throw new BookNotFoundExceptions("Lo sentimos, el libro con título "+ bookTitle + " no se encontró.");
        }
    }

    //Input data validations for books in case we have multiple matches in the Gutendex search
    public static void verifyGutendexInputIsValid(int input, int elementsNumber) throws InvalidOptionsExceptions, InvalidOptionsExceptions {
        if (input < 0 || input > elementsNumber) {
            throw new InvalidOptionsExceptions("Opción inválida, intente de nuevo con las opciones disponibles en el menú.");
        } else if (input == 0) {
            System.out.println("Programa finalizado. Cerrando aplicación...");
            System.exit(0);
        }
    }

    //Input data validation for year. Option 4 of main menu
    public static  void verifyYearsFormat(int inputYear) throws InvalidOptionsExceptions {
        if (inputYear < -4000 || inputYear > 2024) {
            throw new InvalidOptionsExceptions("Opción inválida, intente con otros valores");

        }
    }

    //Input Data validation for searching books by language
    public static void verifyOptionsForLanguageMeu(int input) throws InvalidOptionsExceptions {
        if (input > 7 || input < 1){
            throw new InvalidOptionsExceptions("Opción inválida, seleccione una opción del menú.");
        }
    }


}
