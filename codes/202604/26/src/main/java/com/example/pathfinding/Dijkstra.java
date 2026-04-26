package com.example.pathfinding;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 다익스트라(Dijkstra) 알고리즘을 구현하여 최단 경로를 찾는 클래스입니다.
 */
public class Dijkstra {

    /**
     * 다익스트라 알고리즘을 실행하여 주어진 시작 노드로부터 다른 모든 노드까지의 최단 경로를 계산합니다.
     *
     * @param graph 최단 경로를 계산할 그래프
     * @param startNodeName 시작 노드의 이름
     * @throws IllegalArgumentException 시작 노드가 그래프에 존재하지 않을 경우 발생
     */
    public void execute(Graph graph, String startNodeName) {
        Node startNode = graph.getNode(startNodeName);
        if (startNode == null) {
            throw new IllegalArgumentException("시작 노드 '" + startNodeName + "'가 그래프에 존재하지 않습니다.");
        }

        // 모든 노드의 거리를 초기화하고 시작 노드의 거리를 0으로 설정합니다.
        for (Node node : graph.getNodes()) {
            node.setDistance(Integer.MAX_VALUE);
            node.setPrevious(null);
        }
        startNode.setDistance(0);

        // 방문하지 않은 노드를 저장하는 우선순위 큐 (거리가 짧은 노드가 우선)
        PriorityQueue<Node> unsettledNodes = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        unsettledNodes.add(startNode);

        // 방문한 노드를 저장하는 집합
        Set<Node> settledNodes = new HashSet<>();

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll(); // 현재 가장 거리가 짧은 노드를 가져옵니다.

            if (settledNodes.contains(currentNode)) {
                continue; // 이미 방문한 노드이면 건너뜁니다.
            }

            settledNodes.add(currentNode); // 현재 노드를 방문한 것으로 표시합니다.

            // 현재 노드의 인접 노드들을 확인합니다.
            for (Graph.Edge edge : graph.getAdjacencies(currentNode)) {
                Node neighbor = edge.destination;
                if (!settledNodes.contains(neighbor)) {
                    calculateMinimumDistance(currentNode, neighbor, edge.weight);
                    unsettledNodes.add(neighbor); // 방문하지 않은 인접 노드를 우선순위 큐에 추가합니다.
                }
            }
        }
    }

    /**
     * 출발 노드를 통해 인접 노드로 가는 새로운 경로의 거리를 계산하고, 더 짧으면 업데이트합니다.
     *
     * @param evaluationNode 현재 평가 중인 노드 (출발 노드)
     * @param targetNode 업데이트할 인접 노드
     * @param weight 두 노드 사이의 간선 가중치
     */
    private void calculateMinimumDistance(Node evaluationNode, Node targetNode, int weight) {
        int newDistance = evaluationNode.getDistance() + weight;
        if (newDistance < targetNode.getDistance()) {
            targetNode.setDistance(newDistance);
            targetNode.setPrevious(evaluationNode);
        }
    }

    /**
     * 최단 경로를 역추적하여 출력합니다.
     *
     * @param targetNode 최단 경로를 찾을 목적지 노드
     */
    public void printShortestPath(Node targetNode) {
        System.out.println("노드 '" + targetNode.getName() + "'까지의 최단 경로:");
        if (targetNode.getDistance() == Integer.MAX_VALUE) {
            System.out.println("  도달할 수 없습니다.");
            return;
        }

        List<Node> path = new LinkedList<>();
        for (Node node = targetNode; node != null; node = node.getPrevious()) {
            path.add(0, node); // 경로를 역순으로 추가하여 올바른 순서를 만듭니다.
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" (총 거리: " + targetNode.getDistance() + ")");
    }
}
