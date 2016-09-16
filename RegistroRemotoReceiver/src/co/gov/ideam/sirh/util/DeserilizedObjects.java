package co.gov.ideam.sirh.util;
  public  class DeserilizedObjects{
        private Object objeto;
        private String className;

        public DeserilizedObjects(Object obj, String name){
            this.objeto = obj;
            this.className = name;
        }
        public Object getObjeto() {
            return objeto;
        }

        public void setObjeto(Object objeto) {
            this.objeto = objeto;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
