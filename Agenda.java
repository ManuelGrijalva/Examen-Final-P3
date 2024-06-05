package Servicio;

import Modelo.Contacto;
import Modelo.NodoContacto;
import java.io.*;
import java.time.LocalDate;

public class Agenda {
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
        }
    }

    // Búsqueda por múltiples criterios
    public Contacto buscarContacto(String nombre, Long telefono, String correoElectronico) {
        return buscar(this.raiz, nombre, telefono, correoElectronico);
    }

    private Contacto buscar(NodoContacto nodo, String nombre, Long telefono, String correoElectronico) {
        if (nodo == null) {
            return null;
        }
        if (matches(nodo.getContacto(), nombre, telefono, correoElectronico)) {
            return nodo.getContacto();
        }
        Contacto result = buscar(nodo.getIzdo(), nombre, telefono, correoElectronico);
        if (result != null) {
            return result;
        }
        return buscar(nodo.getDcho(), nombre, telefono, correoElectronico);
    }

    private boolean matches(Contacto contacto, String nombre, Long telefono, String correoElectronico) {
        if (nombre != null && !contacto.getNombre().equals(nombre)) {
            return false;
        }
        if (telefono != null && contacto.getTelefono() != telefono) {
            return false;
        }
        return correoElectronico == null || contacto.getCorreoElectronico().equals(correoElectronico);
    }

    public void eliminarContacto(String nombre) {
        this.raiz = eliminar(this.raiz, nombre);
    }

    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }

            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo();
        }
        return actual;
    }

    public void mostrarContactos() {
        inOrden(this.raiz);
    }

    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            System.out.println("Nombre: " + nodo.getContacto().getNombre() +
                    ", Teléfono: " + nodo.getContacto().getTelefono() +
                    ", Correo Electrónico: " + nodo.getContacto().getCorreoElectronico() +
                    ", Fecha de Nacimiento: " + nodo.getContacto().getFechaNacimiento());
            inOrden(nodo.getDcho());
        }
    }

    // Métodos de serialización
    public void guardarAgenda(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(raiz);
        }
    }

    public void cargarAgenda(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            raiz = (NodoContacto) ois.readObject();
        }
    }
}
