package Volos.MicroService.Lab2.model.Enums;

import Volos.MicroService.Lab2.exception.ObjectNotFoundException;


public enum TypeOfTicket {
        NORMAL ("Normal"),
        COOL ("Cool"),
        VIP ("VIP");

        private String title;

        TypeOfTicket(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static TypeOfTicket getTypeByUrl(String url) throws ObjectNotFoundException {
                for (TypeOfTicket env : values()) {
                        if (env.getTitle().equals(url)) {
                                return env;
                        }
                }
                throw new ObjectNotFoundException(TypeOfTicket.class.getName(), url);
        }

        @Override
        public String toString() {
            return title;
        }
}
