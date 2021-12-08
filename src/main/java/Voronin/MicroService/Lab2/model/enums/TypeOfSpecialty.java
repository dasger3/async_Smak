package Voronin.MicroService.Lab2.model.enums;

import Voronin.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfSpecialty {
    ENGINEER ("Engineer"),
    ECONOMIST ("Economist"),
    SCIENTIST ("Scientist");

    private String title;

    TypeOfSpecialty(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TypeOfSpecialty getTypeByUrl(String url) throws ObjectNotFoundException {
        for (TypeOfSpecialty env : values()) {
            if (env.getTitle().equals(url)) {
                return env;
            }
        }
        throw new ObjectNotFoundException(TypeOfSpecialty.class.getName(), url);
    }

    @Override
    public String toString() {
        return title;
    }
}
