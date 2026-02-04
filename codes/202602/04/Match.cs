using System;

// 두 선수 간의 경기를 나타내는 클래스입니다.
public class Match
{
    public Player Player1 { get; }
    public Player Player2 { get; }

    private static readonly Random _random = new Random();

    public Match(Player player1, Player player2)
    {
        Player1 = player1;
        Player2 = player2;
    }

    // 경기를 시뮬레이션하고 승자를 반환합니다.
    // 각 선수의 Skill 값에 기반하여 승리 확률을 계산합니다.
    public Player Simulate()
    {
        int totalSkill = Player1.Skill + Player2.Skill;
        if (totalSkill == 0) // 두 선수 모두 실력이 0인 경우
        {
            return _random.Next(2) == 0 ? Player1 : Player2;
        }

        // Player1이 이길 확률 (백분율)
        double player1WinProbability = (double)Player1.Skill / totalSkill * 100;
        
        int randomNumber = _random.Next(0, 101); // 0부터 100까지의 난수 생성

        if (randomNumber <= player1WinProbability)
        {
            return Player1; // Player1 승리
        }
        else
        {
            return Player2; // Player2 승리
        }
    }
}