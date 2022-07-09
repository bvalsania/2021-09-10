package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private Graph<Business, DefaultWeightedEdge> grafo;
	private Map<String, Business> idMap;
	
	
	public Model() {
		dao = new YelpDao();
		idMap = new HashMap<>();
		
		this.dao.getAllBusiness(idMap);
	}
	
	public String creaGrafo(String citta) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(citta, idMap));
		
		for(Distanza d1 : this.dao.getDistanza(citta, idMap)) {
			for(Distanza d2 : this.dao.getDistanza(citta, idMap)) {
				if(!d1.equals(d2)) {
					double peso = LatLngTool.distance(d1.getDistanza(), d2.getDistanza(), LengthUnit.KILOMETER);
					Graphs.addEdge(this.grafo, d1.getB(), d2.getB(), peso);
				}
			}
		}
		
		return "Il grafo creato ha "+this.grafo.vertexSet().size()+" vertici e "
				+ ""+this.grafo.edgeSet().size()+" archi.";
	}
	
	public List<String> getCitta(){
		return this.dao.getCitta();
	}
	
	public List<Business> getLocali(){
		return new ArrayList<>(this.grafo.vertexSet());
	}
	
	
	public Nuova getLocaleD(Business b) {
		double distanzaMax = 0.0;
		Nuova s = null;
		
		
		List<Business> vicini = Graphs.neighborListOf(this.grafo, b);
		for(Business i : vicini ) {
			Nuova n = new Nuova(i.getBusinessName(), grafo.getEdgeWeight(grafo.getEdge(i, b)));
			double peso =  grafo.getEdgeWeight(grafo.getEdge(i, b));
		
			if(peso>distanzaMax) {
				distanzaMax = peso;
				s =n ;
			}
		}
		return s;
	}
	
}
