package org.example;

import org.utils.Reader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        java.io.Reader reader = new Reader(System.in);

        int nVertices = reader.nextInt();
        int nEdges = reader.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);
        int start = 0;
        graph[0].distance = 0;
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = new Vertex(i);
        }

        //Read all edges
        for (int i = 0; i < numberOfEdges; ++i) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        long distance = Long.MAX_VALUE;
        boolean visited = false;
        Vertex previous = null;
        List<Vertex> adjecentVertices = new ArrayList<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjecentVertices.add(v);
        }
    }
}

