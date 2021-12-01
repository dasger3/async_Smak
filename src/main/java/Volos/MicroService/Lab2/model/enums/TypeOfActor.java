package Volos.MicroService.Lab2.model.enums;

import Volos.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfActor {
        JUNIOR ("Junior"),
        MIDDLE ("Middle"),
        SENIOR ("Senior");

        private String title;

        TypeOfActor(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static TypeOfActor getTypeByUrl(String url) throws ObjectNotFoundException {
                for (TypeOfActor env : values()) {
                        if (env.getTitle().equals(url)) {
                                return env;
                        }
                }
                throw new ObjectNotFoundException(TypeOfActor.class.getName(), url);
        }

        @Override
        public String toString() {
            return title;
        }
}
