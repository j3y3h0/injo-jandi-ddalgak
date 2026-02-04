using System;
using System.Collections.Generic;
using System.Linq;

// FSL(Freeca StarCraft League) 스프링 시즌의 경기 결과를 간단히 시뮬레이션하는 프로젝트입니다.
// 뉴스 기사를 바탕으로, 주요 선수들을 포함하여 간단한 토너먼트 구조를 모델링합니다.
public class Program
{
    public static void Main(string[] args)
    {
        Console.WriteLine("== FSL 스프링 시즌 경기 시뮬레이션 시작 ==");
        Console.WriteLine();

        // 뉴스에 언급된 선수들을 기반으로 플레이어 생성
        var players = new List<Player>
        {
            new Player("권창환", 85),
            new Player("황세종", 88),
            new Player("임태산", 82),
            new Player("김태신", 80)
        };

        Console.WriteLine("참가 선수:");
        foreach (var p in players)
        {
            Console.WriteLine($"- {p.Name} (Skill: {p.Skill})");
        }
        Console.WriteLine();

        // 선수들을 무작위로 두 그룹으로 나눔
        var random = new Random();
        var shuffledPlayers = players.OrderBy(p => random.Next()).ToList();

        var player1 = shuffledPlayers[0];
        var player2 = shuffledPlayers[1];
        var player3 = shuffledPlayers[2];
        var player4 = shuffledPlayers[3];
        
        // 1라운드 경기 진행
        Console.WriteLine("--- 1라운드 ---");
        var match1 = new Match(player1, player2);
        Player winner1 = match1.Simulate();
        Console.WriteLine($"{match1.Player1.Name} vs {match1.Player2.Name} -> 승자: {winner1.Name}");

        var match2 = new Match(player3, player4);
        Player winner2 = match2.Simulate();
        Console.WriteLine($"{match2.Player1.Name} vs {match2.Player2.Name} -> 승자: {winner2.Name}");
        Console.WriteLine();

        // 승자전 진행
        Console.WriteLine("--- 승자전 ---");
        var finalMatch = new Match(winner1, winner2);
        Player champion = finalMatch.Simulate();
        Console.WriteLine($"{finalMatch.Player1.Name} vs {finalMatch.Player2.Name} -> 최종 승자: {champion.Name}");
        Console.WriteLine();
        
        Console.WriteLine($"🎉 {champion.Name} 선수가 최종 우승했습니다! 🎉");
    }
}