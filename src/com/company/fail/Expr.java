package com.company.fail;

import java.util.List;

abstract class Expr {
  interface Visitor<R> {
    R visitAssignExpr(Assign expr);
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitLogicalExpr(Logical expr);
    R visitUnaryExpr(Unary expr);
    R visitTernaryExpr(Ternary expr);
    R visitVariableExpr(Variable expr);
  }

  static class Assign extends Expr {
    Assign(Token name, Expr value) {
      this.name = name;
      this.value = value;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitAssignExpr(this);
    }

    final Token name;
    final Expr value;
  }

  static class Binary extends Expr {
    Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }

  static class Grouping extends Expr {
    Grouping(Expr expression) {
      this.expression = expression;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }

    final Expr expression;
  }

  static class Literal extends Expr {
    Literal(Object value) {
      this.value = value;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }

    final Object value;
  }

  static class Logical extends Expr {
    Logical(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLogicalExpr(this);
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }

  static class Unary extends Expr {
    Unary(Token operator, Expr right, Boolean postfix) {
      this.operator = operator;
      this.right = right;
      this.postfix = postfix;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }

    final Token operator;
    final Expr right;
    final Boolean postfix;
  }

  static class Ternary extends Expr {
    Ternary(Token operator, Expr expr, Expr trueExpr, Expr falseExpr) {
      this.operator = operator;
      this.expr = expr;
      this.trueExpr = trueExpr;
      this.falseExpr = falseExpr;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitTernaryExpr(this);
    }

    final Token operator;
    final Expr expr;
    final Expr trueExpr;
    final Expr falseExpr;
  }

  static class Variable extends Expr {
    Variable(Token name) {
      this.name = name;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitVariableExpr(this);
    }

    final Token name;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
