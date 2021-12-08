package Voronin.MicroService.Lab2.model.enums;

import Voronin.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfMark {
    FAIRLY("Fairly"),
    AVERAGE("Average"),
    EXCELLENT("Excellent");

    private String title;

    TypeOfMark(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TypeOfMark getTypeByUrl(String url) throws ObjectNotFoundException {
        for (TypeOfMark env : values()) {
            if (env.getTitle().equals(url)) {
                return env;
            }
        }
        throw new ObjectNotFoundException(TypeOfMark.class.getName(), url);
    }

    @Override
    public String toString() {
        return title;
    }
}
