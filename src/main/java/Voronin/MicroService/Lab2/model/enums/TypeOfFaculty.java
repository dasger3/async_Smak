package Voronin.MicroService.Lab2.model.enums;

import Voronin.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfFaculty {
        TEF ("TEF"),
        IPSA ("IPSA"),
        FIOT ("FIOT");

        private final String title;

        TypeOfFaculty(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static TypeOfFaculty getTypeByUrl(String url) throws ObjectNotFoundException {
                for (TypeOfFaculty env : values()) {
                        if (env.getTitle().equals(url)) {
                                return env;
                        }
                }
                throw new ObjectNotFoundException(TypeOfFaculty.class.getName(), url);
        }

        @Override
        public String toString() {
            return title;
        }
}
