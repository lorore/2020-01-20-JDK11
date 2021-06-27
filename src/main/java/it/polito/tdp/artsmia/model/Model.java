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
		System.out.println(result);
		return result;
	}

}
