package kr.airi.ojt.board.global.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum YnCode implements BoardEnumerable {
  Y(0), // 0
  N(1), // 1
  ;

  private final int value;

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public String getKey() {
    return name();
  }
}
