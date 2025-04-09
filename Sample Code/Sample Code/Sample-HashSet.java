package org.example;

import org.utils.Reader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    static Reader reader = new Reader(System.in);

    public static void main(String[] args) {


        int nVertices = reader.nextInt();
        int nEdges = reader.nextInt();

        Vertex[] graph = readGraph(nVertices, nEdges);
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = new Vertex(i + 1);
        }

        //Read all edges
        for (int i = 0; i < numberOfEdges; ++i) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            vertices[u - 1].addNeighbor(vertices[v - 1]);
            vertices[v - 1].addNeighbor(vertices[u - 1]);
        }
        return vertices;
    }

    static class Vertex {
        int id;
        HashSet<Vertex> adjecentVertices = new HashSet<Vertex>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            adjecentVertices.add(v);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex) {
                return ((Vertex) obj).id == id;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}

