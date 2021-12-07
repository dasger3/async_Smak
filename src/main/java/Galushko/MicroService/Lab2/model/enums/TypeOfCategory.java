package Galushko.MicroService.Lab2.model.enums;

import Galushko.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfCategory {
        A ("A"),
        B ("B"),
        C ("C");

        private final String title;

        TypeOfCategory(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static TypeOfCategory getTypeByUrl(String url) throws ObjectNotFoundException {
                for (TypeOfCategory env : values()) {
                        if (env.getTitle().equals(url)) {
                                return env;
                        }
                }
                throw new ObjectNotFoundException(TypeOfCategory.class.getName(), url);
        }

        @Override
        public String toString() {
            return title;
        }
}
