package addAnnotation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Visitor extends VoidVisitorAdapter<Void> {

	final List<String> importList = new ArrayList<>();
	final Deque<Integer> insertAnnotationLine = new ArrayDeque<>();
	Integer importFirstLine = null;
	Integer importMobcastFirstLine = null;

//	    @Override
//	    public void visit(ClassOrInterfaceDeclaration c, Void arg) {
//	        System.out.println("クラス名: " + c.getNameAsString());
//	        System.out.println("\t範囲: " + c.getRange().orElse(null));
//	        System.out.println("\t継承: " + c.getExtendedTypes());
//	        System.out.println();
//	        super.visit(c, arg);
//	    }

	@Override
	public void visit(MethodDeclaration m, Void arg) {
//		System.out.println("メソッド名: " + m.getNameAsString());
//		System.out.println("\t開始行数: " + m.getRange().get().begin.line);

		// アノテーション存在チェック
		if(!m.isAnnotationPresent("WithSpan")) {
//			System.out.println("\tNG アノテーションがない");
			insertAnnotationLine.push(m.getRange().get().begin.line);
		} else {
//			System.out.println("\tOK アノテーションがあるので何もしない");
		}

//		System.out.println();

		super.visit(m, arg);
	}

	@Override
	public void visit(ConstructorDeclaration c, Void arg) {
//		System.out.println("コンストラクター名: " + c.getNameAsString());
//		System.out.println("\t開始行数: " + c.getRange().get().begin.line);

		// アノテーション存在チェック
		if(!c.isAnnotationPresent("WithSpan")) {
//			System.out.println("\tNG アノテーションがない");
			insertAnnotationLine.push(c.getRange().get().begin.line);
		} else {
//			System.out.println("\tOK アノテーションがあるので何もしない");
		}

//		System.out.println();

		super.visit(c, arg);
	}

//	    @Override
//	    public void visit(FieldDeclaration f, Void arg) {
//	        for (VariableDeclarator v : f.getVariables()) {
//	            System.out.println("バリアブル名: " + v.getNameAsString());
//	            System.out.println("\t範囲: " + v.getRange().orElse(null));
//	            System.out.println("\t型: " + v.getType().asString());
//	            System.out.println();
//	        }
//	        super.visit(f, arg);
//	    }

	@Override
	public void visit(ImportDeclaration i, Void arg) {
		importList.add(i.getNameAsString());
		if(Objects.isNull(importFirstLine)) {
//			System.out.println("import開始行数: " + i.getRange().get().begin.line);
			importFirstLine = i.getRange().get().begin.line;
		}
		if(Objects.isNull(importMobcastFirstLine)) {
			if(i.getNameAsString().contains("jp.mobcast")) {
//				System.out.println("import(jp.mobcast系)開始行数: " + i.getRange().get().begin.line);
				importMobcastFirstLine = i.getRange().get().begin.line;
			}
		}

//		System.out.println();

		super.visit(i, arg);
	}
}