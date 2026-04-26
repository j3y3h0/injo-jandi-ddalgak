package com.example.pathfinding;

/**
 * 그래프의 노드를 나타내는 클래스입니다.
 * 각 노드는 고유한 이름을 가지며, 최단 경로 계산을 위한 거리 값과 이전 노드 정보를 저장합니다.
 */
public class Node {
    private String name; // 노드의 이름
    private int distance; // 시작 노드로부터의 거리
    private Node previous; // 최단 경로 상의 이전 노드

    /**
     * 새로운 노드를 생성합니다.
     *
     * @param name 노드의 고유 이름
     */
    public Node(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE; // 초기 거리는 무한대로 설정 (도달 불가능)
        this.previous = null; // 초기 이전 노드는 없음
    }

    /**
     * 노드의 이름을 반환합니다.
     *
     * @return 노드의 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 시작 노드로부터의 거리를 반환합니다.
     *
     * @return 거리 값
     */
    public int getDistance() {
        return distance;
    }

    /**
     * 시작 노드로부터의 거리를 설정합니다.
     *
     * @param distance 새로운 거리 값
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * 최단 경로 상의 이전 노드를 반환합니다.
     *
     * @return 이전 노드
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * 최단 경로 상의 이전 노드를 설정합니다.
     *
     * @param previous 이전 노드
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return name;
    }
}
