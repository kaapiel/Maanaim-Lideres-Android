package br.com.igreja.cellapp.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Membro;

public class WebClientCellApp {

    public int[] convertStringArrayToIntArray(String array) {

        String[] ids = array.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] liderMembroId = new int[ids.length];

        for (int i = 0; i < ids.length; i++) {
            try {
                liderMembroId[i] = Integer.parseInt(ids[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            }
        }

        return liderMembroId;
    }

    public Celula getCelulaAssociadaComMembro(List<Celula> celulas, Membro m) {

        for (Celula c : celulas) {
            if (m.getIdCelula().equals(c.getIdCelula())) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Membro> getListaDeMembrosDaCelula(List<Membro> membros, Integer idCelula) {

        ArrayList<Membro> ms = new ArrayList<>();

        for (Membro m : membros) {
            if (m.getIdCelula().equals(idCelula)) {
                ms.add(m);
            }
        }
        return ms;
    }

    public ArrayList<Membro> getListaDeLideres(List<Celula> celulas, List<Membro> membros) {

        ArrayList<Membro> ms = new ArrayList<>();
        for (Celula c : celulas) {
            for (Membro m : membros) {
                for (int i = 0; i < c.getLiderMembroId().length; i++) {
                    if (c.getLiderMembroId()[i] == m.getIdMembro()) {
                        ms.add(m);
                    }
                }
            }
        }
        return ms;
    }

    public ArrayList<Membro> getListaDeLideresDaCelula(List<Membro> membros, Celula celula) {

        ArrayList<Membro> lideres = new ArrayList<>();
        for (Membro m : membros) {
            for (int i = 0; i < celula.getLiderMembroId().length; i++) {
                if (celula.getLiderMembroId()[i] == m.getIdMembro()) {
                    lideres.add(m);
                }
            }
        }
        return lideres;
    }


    public ArrayList<Celula> getListaDeCelulasPorNome(ArrayList<Celula> celulas, String query) {

        ArrayList<Celula> celulasQuery = new ArrayList<>();
        for (Celula c : celulas) {
            if (c.getNome().contains(query)) {
                celulasQuery.add(c);
            }
        }

        return celulasQuery;
    }

    public ArrayList<Membro> getListaDeMembrosPorNome(ArrayList<Membro> membros, String query) {

        ArrayList<Membro> membrosQuery = new ArrayList<>();

        for (Membro m : membros) {
            if (m.getNome().contains(query)) {
                membrosQuery.add(m);
            }
        }
        return membrosQuery;
    }


    public ArrayList<Membro> getListaDeMembrosPorNomeNasc(ArrayList<Membro> membros, String query) {

        ArrayList<Membro> membrosQuery = new ArrayList<>();
        for (Membro m : membros) {
            if (m.getNome().contains(query)) {
                membrosQuery.add(m);
            }
        }
        return membrosQuery;
    }

    public ArrayList<Membro> getListaDeAniversariantes(ArrayList<Membro> membros, String mes) {

        ArrayList<Membro> aniversariantes = new ArrayList<>();
        for (Membro m : membros) {
            if (m.getDataNasc().split("/")[1].equals(mes)) {
                aniversariantes.add(m);
            }
        }
        return aniversariantes;
    }

    public Celula getCelulaPorId(ArrayList<Celula> celulas, Integer idCelula) {

        for (Celula c : celulas) {
            if(c.getIdCelula().equals(idCelula)){
                return c;
            }
        }
        return null;
    }
}
