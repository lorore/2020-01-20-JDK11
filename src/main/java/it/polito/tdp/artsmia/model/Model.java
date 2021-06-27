package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private Graph<Integer, DefaultWeightedEdge> graph;
	private ArtsmiaDAO dao;
	private Integer max;
	private List<Integer> soluzione;
	
	public Model() {
		dao=new ArtsmiaDAO();
	}
	
	public String creaGrafo(String p) {
		graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		String result="";
		List<Integer> vertici=this.dao.getVertici(p);
		Graphs.addAllVertices(graph, vertici);
		result="Num vertici: "+this.graph.vertexSet().size();
		List<Adiacenza> archi=this.dao.getArchi(p);
		for(Adiacenza a: archi) {
			if(vertici.contains(a.getA1()) && vertici.contains(a.getA2())) {
				if(graph.getEdge(a.getA1(), a.getA2()) == null) {
					Graphs.addEdge(graph, a.getA1(), a.getA2(), a.getPeso());
				}
			}
			
		}
		result+=" num archi: "+this.graph.edgeSet().size();
		//System.out.println(result);
		return result;
	}
	
	public List<String> getP(){
		return this.dao.getP();
	}
	
	public List<Adiacenza> getCoppie(){
		List<Adiacenza> result=new ArrayList<>();
		for(DefaultWeightedEdge e: this.graph.edgeSet()) {
			result.add(new Adiacenza(graph.getEdgeSource(e) , graph.getEdgeTarget(e) , graph.getEdgeWeight(e)));
		}
		Collections.sort(result);
		//System.out.println(result);
		return result;
	}

	
	public List<Integer> avviaRicorsione(int id){
		this.max=0;
		this.soluzione=new ArrayList<>();
		List<Integer> parziale=new ArrayList<>();
		parziale.add(id);
		this.doRicorsione(parziale, 1);
		System.out.println(soluzione);
		return this.soluzione;
	}
	
	private void doRicorsione(List<Integer> parziale, int livello) {
		
		
		Integer ultimo=parziale.get(parziale.size()-1);
		for(Integer a: Graphs.neighborListOf(graph, ultimo)) {
			if(!parziale.contains(a)) {
				double peso=graph.getEdgeWeight(graph.getEdge(ultimo, a));
				if(this.isUguale(peso, parziale)) {
					//pesi tutti uguali, posso aggiungere
					parziale.add(a);
					if(parziale.size()>this.max) {
						this.max=parziale.size();
						this.soluzione=new ArrayList<>(parziale);
					}
					this.doRicorsione(parziale, livello+1);
					parziale.remove(parziale.size()-1);
				}
			}
		}
		
		
	}
	
	
	private boolean isUguale(double peso, List<Integer> parziale) {
		if(parziale.size()==1)
			return true;
		
	    for(int i=0; i<parziale.size()-1; i++) {
	    	if(peso!=graph.getEdgeWeight(graph.getEdge(parziale.get(i), parziale.get(i+1))))
	    			return false;
	    }
	    return true;
	}
	
	public List<Integer> getV(){
		return new ArrayList<Integer>(this.graph.vertexSet());
	}
	
	public boolean esisteId(Integer id) {
		return this.graph.containsVertex(id);
	}
}
