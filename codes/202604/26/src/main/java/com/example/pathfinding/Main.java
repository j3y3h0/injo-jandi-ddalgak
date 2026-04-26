package com.example.pathfinding;

/**
 * 최단 경로 시뮬레이션 애플리케이션의 메인 클래스입니다.
 * 그래프를 구성하고 다익스트라 알고리즘을 실행하여 최단 경로를 찾습니다.
 */
public class Main {
    public static void main(String[] args) {
        // 1. 그래프 생성 및 노드 추가
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        // 2. 간선 추가 (가중치와 함께)
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 15);
        graph.addEdge("B", "D", 12);
        graph.addEdge("B", "F", 15);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "F", 1);
        graph.addEdge("F", "E", 5);

        // 3. 다익스트라 알고리즘 객체 생성
        Dijkstra dijkstra = new Dijkstra();

        // 4. 'A' 노드에서 시작하여 최단 경로 계산
        String startNodeName = "A";
        System.out.println("--- '" + startNodeName + "' 노드에서 시작하는 최단 경로 시뮬레이션 ---");
        dijkstra.execute(graph, startNodeName);

        // 5. 각 노드까지의 최단 경로 출력
        System.out.println("
--- 최단 경로 결과 ---");
        dijkstra.printShortestPath(nodeB);
        dijkstra.printShortestPath(nodeC);
        dijkstra.printShortestPath(nodeD);
        dijkstra.printShortestPath(nodeE);
        dijkstra.printShortestPath(nodeF);

        System.out.println("
시작 노드를 'B'로 변경하여 다시 계산합니다.");
        // 모든 노드의 상태를 초기화해야 합니다.
        for (Node node : graph.getNodes()) {
            node.setDistance(Integer.MAX_VALUE);
            node.setPrevious(null);
        }
        Node nodeB_re = graph.getNode("B");
        String startNodeName2 = "B";
        System.out.println("--- '" + startNodeName2 + "' 노드에서 시작하는 최단 경로 시뮬레이션 ---");
        dijkstra.execute(graph, startNodeName2);

        System.out.println("
--- 최단 경로 결과 ---");
        dijkstra.printShortestPath(nodeA);
        dijkstra.printShortestPath(nodeC);
        dijkstra.printShortestPath(nodeD);
        dijkstra.printShortestPath(nodeE);
        dijkstra.printShortestPath(nodeF);
    }
}
