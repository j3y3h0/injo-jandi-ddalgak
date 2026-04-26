package com.example.pathfinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 그래프 구조를 나타내는 클래스입니다.
 * 노드와 노드 간의 연결(간선)을 관리합니다.
 */
public class Graph {
    private Map<String, Node> nodes; // 노드 이름을 키로 하여 노드 객체를 저장하는 맵
    private Map<Node, List<Edge>> adjacencies; // 각 노드의 인접 리스트

    /**
     * 새로운 그래프를 생성합니다.
     */
    public Graph() {
        this.nodes = new HashMap<>();
        this.adjacencies = new HashMap<>();
    }

    /**
     * 그래프에 노드를 추가합니다.
     *
     * @param node 추가할 노드
     */
    public void addNode(Node node) {
        nodes.put(node.getName(), node);
        adjacencies.putIfAbsent(node, new LinkedList<>()); // 인접 리스트 초기화
    }

    /**
     * 그래프에 양방향 간선을 추가합니다.
     *
     * @param sourceName 출발 노드의 이름
     * @param destinationName 도착 노드의 이름
     * @param weight 간선의 가중치 (거리)
     * @throws IllegalArgumentException 노드가 존재하지 않을 경우 발생
     */
    public void addEdge(String sourceName, String destinationName, int weight) {
        Node source = nodes.get(sourceName);
        Node destination = nodes.get(destinationName);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("출발 또는 도착 노드가 그래프에 존재하지 않습니다.");
        }

        // 양방향 간선 추가
        adjacencies.get(source).add(new Edge(destination, weight));
        adjacencies.get(destination).add(new Edge(source, weight)); // 그래프가 무방향이라고 가정
    }

    /**
     * 주어진 노드 이름에 해당하는 노드를 반환합니다.
     *
     * @param name 찾을 노드의 이름
     * @return 해당 노드 객체, 없으면 null
     */
    public Node getNode(String name) {
        return nodes.get(name);
    }

    /**
     * 그래프의 모든 노드를 반환합니다.
     *
     * @return 모든 노드의 컬렉션
     */
    public Iterable<Node> getNodes() {
        return nodes.values();
    }

    /**
     * 주어진 노드의 인접 간선 리스트를 반환합니다.
     *
     * @param node 인접 간선을 찾을 노드
     * @return 해당 노드의 인접 간선 리스트
     */
    public List<Edge> getAdjacencies(Node node) {
        return adjacencies.get(node);
    }

    /**
     * 그래프의 간선을 나타내는 내부 클래스입니다.
     */
    public static class Edge {
        public final Node destination; // 간선이 향하는 노드
        public final int weight; // 간선의 가중치

        public Edge(Node destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
