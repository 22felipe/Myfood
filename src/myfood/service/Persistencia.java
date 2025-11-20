package myfood.service;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import myfood.models.Pedidos;
import myfood.models.empresas.Empresa;
import myfood.models.entregas.Entregas;
import myfood.models.usuarios.Usuario;

public class Persistencia {

    private static final String ARQUIVO_USUARIOS = "usuarios.xml";
    private static final String ARQUIVO_EMPRESAS = "empresas.xml";
    private static final String ARQUIVO_PEDIDOS = "pedidos.xml";
    private static final String ARQUIVO_Entregas = "entregas.xml";

    // ---------- SALVAR ----------
    public static void salvar(List<Usuario> usuarios, List<Empresa> empresas, List<Pedidos> pedidos, List<Entregas> entregas) {
        salvarObjeto(usuarios, ARQUIVO_USUARIOS);
        salvarObjeto(empresas, ARQUIVO_EMPRESAS);
        salvarObjeto(pedidos, ARQUIVO_PEDIDOS);
        salvarObjeto(entregas, ARQUIVO_Entregas);
    }

    private static void salvarObjeto(Object obj, String arquivo) {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(arquivo)))) {
            encoder.writeObject(obj);
        } catch (Exception e) {
            System.err.println("Erro ao salvar " + arquivo + ": " + e.getMessage());
        }
    }

    // ---------- CARREGAR ----------
    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios() {
        return (List<Usuario>) carregarObjeto(ARQUIVO_USUARIOS);
    }

    @SuppressWarnings("unchecked")
    public static List<Empresa> carregarEmpresas() {
        return (List<Empresa>) carregarObjeto(ARQUIVO_EMPRESAS);
    }

    @SuppressWarnings("unchecked")
    public static List<Pedidos> carregarPedidos() {
        return (List<Pedidos>) carregarObjeto(ARQUIVO_PEDIDOS);
    }

    public static List<Entregas> carregarEntregas() {
        return (List<Entregas>) carregarObjeto(ARQUIVO_Entregas);
    }

    private static Object carregarObjeto(String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return new ArrayList<>();

        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(arquivo)))) {
            return decoder.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar " + arquivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
