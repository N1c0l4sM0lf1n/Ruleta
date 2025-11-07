package org.example.Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private final File archivo;

    public RepositorioArchivo(String ruta) {
        this.archivo = new File(ruta);
    }

    @Override
    public void guardarResultado(Resultado resultado) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(resultado.getNumero() + ";" +
                    resultado.getEtiqueta() + ";" +
                    resultado.getMonto() + ";" +
                    resultado.isAcierto());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        List<Resultado> lista = new ArrayList<>();

        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                int numero = Integer.parseInt(p[0]);
                String etiqueta = p[1];
                int monto = Integer.parseInt(p[2]);
                boolean acierto = Boolean.parseBoolean(p[3]);
                lista.add(new Resultado(numero, etiqueta, monto, acierto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
