using System;

// 경기에 참여하는 선수를 나타내는 클래스입니다.
public class Player
{
    // 선수의 이름
    public string Name { get; }

    // 선수의 실력 (0-100). 높을수록 승리 확률이 높아집니다.
    public int Skill { get; }

    public Player(string name, int skill)
    {
        if (string.IsNullOrWhiteSpace(name))
            throw new ArgumentException("선수 이름은 비어 있을 수 없습니다.", nameof(name));
        if (skill < 0 || skill > 100)
            throw new ArgumentOutOfRangeException(nameof(skill), "실력 수치는 0과 100 사이여야 합니다.");

        Name = name;
        Skill = skill;
    }
}