// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////

import java.io.PrintStream;
import java.util.*;
			  



/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    protected AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    public AbstractSymbol computeType(ClassTable classTable) { return null; }

}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;

    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            // sm: changed 'n + 1' to 'n + 2' to match changes elsewhere
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */

        /* Phase 1: Build inheritence graph */
	ClassTable classTable = new ClassTable(classes);
	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
        /* Phase 2: Perform type checking */
        classTable.doTypeCheck();
	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	} else {
            dump_with_types(System.err, 0);
        }
        
    }

}


/** Defines AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    /** Creates "class_c" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public AbstractSymbol getFilename() { return filename; }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }

    /* First traversal through the program */
    public void findAttrAndMethodTypes(ClassTable classTable){

        /* Maps attributes to their types */
        HashMap<AbstractSymbol, AbstractSymbol> attrToType;
        /* Maps methods to their signatures */
        HashMap<AbstractSymbol, List> methodToSignature; 

        if (parent.equals(TreeConstants.No_class)){
            attrToType = new HashMap<AbstractSymbol, AbstractSymbol>();
            methodToSignature = new HashMap<AbstractSymbol, List>();
        } else {
            attrToType = (HashMap<AbstractSymbol, AbstractSymbol>) 
                          classTable.classToAttrMap.get(parent).clone();
            methodToSignature = (HashMap<AbstractSymbol, List>) 
                                 classTable.classToMethodMap.get(parent).clone();
        }
        classTable.classToAttrMap.put(name, attrToType);
        classTable.classToMethodMap.put(name, methodToSignature);

        /* Populate method and attribute type info structures */
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
            /* Check feature type at runtime */
            if (method.class.isAssignableFrom(f.getClass()))
                ((method) f).extractMethodSignature(classTable);
            else
                ((attr) f).extractAttrType(classTable);
        }
        /* Add self variable */
        attrToType.put(TreeConstants.self, TreeConstants.SELF_TYPE);

        if (name.equals(TreeConstants.Main) && !methodToSignature.containsKey(TreeConstants.main_meth)){
            classTable.reportError(this, ClassTable.ERROR_MAIN_NO_MAIN_METHOD);
            return;
        }
    }

    /* Second traversal through the program */
    public void checkAttrAndMethodTypes(ClassTable classTable){

        /* Iterate through features */
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
            Feature f = (Feature) e.nextElement();
            /* Check feature type at runtime */
            if (method.class.isAssignableFrom(f.getClass()))
                ((method) f).checkMethodSignature(classTable);
            else
                ((attr) f).checkAttrType(classTable);
        }
    }

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }

    /* Extract method signature */
    public void extractMethodSignature(ClassTable classTable){
        
        ArrayList<AbstractSymbol> signature = new ArrayList<AbstractSymbol>();

        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
            AbstractSymbol formalType = ((formalc) e.nextElement()).extractFormalType(classTable);
            signature.add(formalType);
        }
        /* Validate return type */
        if (!classTable.isValidType(return_type)){
            classTable.reportError(this, String.format(ClassTable.ERROR_INVALID_RET_TYPE, name));
            return_type = TreeConstants.No_type;
        }
        signature.add(return_type);

        Map<AbstractSymbol, List> methodToSignature = classTable.classToMethodMap
                                                      .get(classTable.currClassName);
        /* If method is inherited or previously defined, match signatures */
        if (methodToSignature.containsKey(name)){
            boolean mismatch = signature.size() != methodToSignature.get(name).size();
            for (int i = 0; !mismatch && i < signature.size(); i++)
                mismatch = !signature.get(i).equals(methodToSignature.get(name).get(i));
            if (mismatch){
                classTable.reportError(this, String.format(classTable.ERROR_METHOD_SIGN_MISMATCH,name));
                signature = (ArrayList<AbstractSymbol>) methodToSignature.get(name);
            }
        }
        methodToSignature.put(name, signature); 
    }

    /* Validate method signature */
    public void checkMethodSignature(ClassTable classTable){
        
        classTable.localVarToType.enterScope();

        /* Collect type information from formals */
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
            ((formalc) e.nextElement()).scopeFormalParams(classTable);
        }
        
        /* verify final type compatibility */
        AbstractSymbol assigned_type = expr.computeType(classTable);
        if (!classTable.isSubtype(return_type, assigned_type)){
            classTable.reportError(this, String.format(ClassTable.ERROR_RET_TYPE_MISMATCH, 
                                   assigned_type, return_type));
        }
        
        classTable.localVarToType.exitScope();
    }
}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

    /* Extract attribute type info */
    public void extractAttrType(ClassTable classTable){
        
        if (!classTable.isValidType(type_decl)){
            classTable.reportError(this, String.format(ClassTable.ERROR_TYPE_NOT_DEF, type_decl));
            type_decl = TreeConstants.No_type; // use No_type for undeclared var
        }
        Map<AbstractSymbol, AbstractSymbol> attrToType = classTable.classToAttrMap.get
                                                         (classTable.currClassName);
        if (attrToType.containsKey(name)){
            classTable.reportError(this, String.format(ClassTable.ERROR_VAR_NAME_IN_USE,name));
            type_decl = TreeConstants.No_type; // use No_type for multiple def
        }
        attrToType.put(name, type_decl);
    }

    /* Validate attribute type */
    public void checkAttrType(ClassTable classTable){
        
        /* Enter scope, even though there's nothing in the stack */
        classTable.localVarToType.enterScope();

        AbstractSymbol assigned_type = init.computeType(classTable);
        if (!classTable.isSubtype(type_decl, assigned_type)){
            classTable.reportError(this, String.format(ClassTable.ERROR_ASSIGN_TYPE_MISMATCH, 
                                                       assigned_type, type_decl));
        }
        /* Exit scope */
        classTable.localVarToType.enterScope();
    }
}


/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Creates "formalc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

    /* Extract formal param type */
    public AbstractSymbol extractFormalType(ClassTable classTable){

        /* Validate formal param type */
        if (!classTable.isValidType(type_decl)){
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_DEF, type_decl));
            type_decl = TreeConstants.No_type;
        }
        if (type_decl.equals(TreeConstants.SELF_TYPE)){
            classTable.reportError(this, ClassTable.ERROR_TYPE_SELF_TYPE);
            type_decl = TreeConstants.No_type;
        }
        return type_decl;
    }

    /* Add to scope */
    public void scopeFormalParams(ClassTable classTable){
        classTable.localVarToType.addId(name, type_decl);
    }

}


/** Defines AST constructor 'branch'.
  <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        
        /* Make sure type is valid */
        if (!classTable.isValidType(type_decl)){
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_DEF, type_decl));
            type_decl = TreeConstants.No_type;
        }
        /* Check to ensure case types are not repeated */
        else if (classTable.caseTypes.contains(type_decl)){
            classTable.reportError(this, String.format(classTable.ERROR_CASE_TYPE_IN_USE, type_decl));
            type_decl = TreeConstants.No_type;
        }
        /* Cannot allow SELF_TYPE in case statement */
        else if (type_decl.equals(TreeConstants.SELF_TYPE)){
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_SELF_TYPE));
            type_decl = TreeConstants.No_type;
        }
        /* Process case expression */
        classTable.localVarToType.enterScope();
        classTable.localVarToType.addId(name, type_decl);
        
        AbstractSymbol return_type = expr.computeType(classTable);
        classTable.localVarToType.exitScope();

        return return_type;
    }
}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        
        /* assigned type */
        type = expr.computeType(classTable);

        /* declared type */
        AbstractSymbol declared_type = null;
        if (classTable.localVarToType.lookup(name) != null)
            declared_type = (AbstractSymbol) classTable.localVarToType.lookup(name);
        if (classTable.classToAttrMap.get(classTable.currClassName).containsKey(name))
            declared_type = (AbstractSymbol) classTable.classToAttrMap.get(classTable.currClassName).get(name);
        /* obj identifier not found */
        if (declared_type == null){
            classTable.reportError(this, String.format(classTable.ERROR_VAR_NOT_DEFINED, name)); 
            type = TreeConstants.No_type;
            return type;
        }
        /* must not assign to self */
        if (name.equals(TreeConstants.self)){
            classTable.reportError(this, classTable.ERROR_ASSIGN_TO_SELF);
            type = TreeConstants.No_type;
            return type;
        }
        if (!classTable.isSubtype(declared_type, type)){
            classTable.reportError(this, String.format(classTable.ERROR_ASSIGN_TYPE_MISMATCH, 
                                                       type, declared_type)); 
            type = TreeConstants.No_type;
        }
        return type;
    }
}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){

        /* Process object type */
        AbstractSymbol expr_type = expr.computeType(classTable);
        AbstractSymbol implied_type = expr_type;
        if (implied_type.equals(TreeConstants.SELF_TYPE))
            implied_type = classTable.currClassName;
        /* Process params */
        List<AbstractSymbol> param_types = new ArrayList<AbstractSymbol>();
        for (int i = 0; i < actual.getLength(); i++){
            param_types.add(((Expression) actual.getNth(i)).computeType(classTable)); 
        }
        /* Invalid static type */
        if (!classTable.isValidType(type_name)){
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_DEF,type_name));
            type = TreeConstants.No_type;
            return type; // can return, since method signature won't be found
        }
        /* Static type can't be SELF_TYPE */
        if (type_name.equals(TreeConstants.SELF_TYPE)) {
            classTable.reportError(this, classTable.ERROR_TYPE_SELF_TYPE);
            type = TreeConstants.No_type;
            return type; // can return, since method signature won't be found
        }
        /* Invalid object type */
        if (implied_type.equals(TreeConstants.No_type)){
            // optimistic error recovery: assume correct type is subtype of type_name
            classTable.reportError(this, classTable.ERROR_UNKNOWN_RET_TYPE);
        }
        /* Expression type must be subtype of cast type */
        if (!classTable.isSubtype(type_name, implied_type)){
            // optimistic error recovery: assume correct type is subtype of type_name
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_SUBTYPE,
                                   implied_type, type_name));
        }
        /* Method name not found */
        if (!classTable.classToMethodMap.get(type_name).containsKey(name)){
            classTable.reportError(this, String.format(classTable.ERROR_METHOD_NOT_DEFINED, name));
            type = TreeConstants.No_type;
            return type; // can return, since method signature won't be found
        } 

        /* Process method param expressions */
        List<AbstractSymbol> exp_method_sign = (List<AbstractSymbol>) classTable.classToMethodMap
                                                                      .get(type_name).get(name);
        if (exp_method_sign.size()-1 != actual.getLength())
            classTable.reportError(this, String.format(classTable.ERROR_WRONG_NUMBER_OF_ARGS, name));
        else 
            for (int i = 0; i < actual.getLength(); i++){
                if (!classTable.isSubtype(exp_method_sign.get(i), param_types.get(i))){
                    classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH, 
                                           exp_method_sign.get(i), param_types.get(i)));
                }
            }
        /* compute return type, accounting for SELF_TYPE */
        type = exp_method_sign.get(exp_method_sign.size()-1);
        if (type.equals(TreeConstants.SELF_TYPE))
            type = expr_type; //WARNING: this can be No_type if expr was buggy
        return type;
    }
}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){

        /* Process object type */
        AbstractSymbol expr_type = expr.computeType(classTable);
        AbstractSymbol implied_type = expr_type;
        if (implied_type.equals(TreeConstants.SELF_TYPE))
            implied_type = classTable.currClassName;
        /* Process params */
        List<AbstractSymbol> param_types = new ArrayList<AbstractSymbol>();
        for (int i = 0; i < actual.getLength(); i++){
            param_types.add(((Expression)actual.getNth(i)).computeType(classTable)); 
        }
        /* Invalid object type */
        if (implied_type.equals(TreeConstants.No_type)){
            classTable.reportError(this, classTable.ERROR_UNKNOWN_RET_TYPE);
            type = TreeConstants.No_type;
            return type; // can return, since method signature won't be found
        }
        /* Method name not found */
        if (!classTable.classToMethodMap.get(implied_type).containsKey(name)){
            classTable.reportError(this, String.format(classTable.ERROR_METHOD_NOT_DEFINED, name));
            type = TreeConstants.No_type;
            return type; // can return, since method signature won't be found
        } 
        /* Process method param expressions */
        List<AbstractSymbol> exp_method_sign = (List<AbstractSymbol>) classTable.classToMethodMap
                                                                      .get(implied_type).get(name);
        for (int i = 0; i < actual.getLength(); i++){
            if (!classTable.isSubtype(exp_method_sign.get(i), param_types.get(i))){
                classTable.reportError( this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH, 
                                        exp_method_sign.get(i), param_types.get(i)));
            }
        }
        /* compute return type, accounting for SELF_TYPE */
        type = exp_method_sign.get(exp_method_sign.size()-1);
        if (type.equals(TreeConstants.SELF_TYPE))
            type = expr_type;
        return type;
    }
}

/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        /* Test predicate */
        AbstractSymbol pred_type = pred.computeType(classTable);
        if (!pred_type.equals(TreeConstants.Bool)){
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Bool, pred_type));
        }
        /* Find least upper bound */
        AbstractSymbol then_type = then_exp.computeType(classTable);
        AbstractSymbol else_type = else_exp.computeType(classTable);
        type = classTable.findLeastUpperBound(then_type, else_type);
        return type;
    }
}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        
        AbstractSymbol pred_type = pred.computeType(classTable);
        if (!pred_type.equals(TreeConstants.Bool))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Bool, pred_type));
        /* process body of loop */
        body.computeType(classTable);

        /* Loop must return object type */
        type = TreeConstants.Object_;
        return type;
    }
}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){

        AbstractSymbol expr_type = expr.computeType(classTable);

        type = TreeConstants.No_type;
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
            AbstractSymbol case_type = ((branch)e.nextElement()).computeType(classTable);
            type = classTable.findLeastUpperBound(type, case_type);
        }
        classTable.caseTypes.clear();
        return type;
    }
}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    type = ((Expression)e.nextElement()).computeType(classTable);
        }
        return type;
    }
}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol init_type = init.computeType(classTable);

        /* Make sure assignment is valid */
        if (!classTable.isValidType(type_decl)){
            classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_DEF, type_decl));
            type_decl = TreeConstants.No_type;
        }
        if (!classTable.isSubtype(type_decl, init_type)){
            classTable.reportError(this, String.format(classTable.ERROR_ASSIGN_TYPE_MISMATCH, 
                                                       init_type, type_decl));
        }

        /* Open scope within let statement */
        classTable.localVarToType.enterScope();
        classTable.localVarToType.addId(identifier, type_decl);

        /* Process rest of the let statement */
        type = body.computeType(classTable);

        classTable.localVarToType.exitScope();
        return type;
    }
}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));

        /* Return int, regardless of error or not */
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));

        /* Return int, regardless of error or not */
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));


        /* Return int, regardless of error or not */
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));


        /* Return int, regardless of error or not */
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        /* Return bool, regardless of error or not */
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));

        /* Return bool, regardless of error or not */
        type = TreeConstants.Bool;
        return type;
    }
}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (e1Type.equals(TreeConstants.Int) || e2Type.equals(TreeConstants.Int) ||
            e1Type.equals(TreeConstants.Bool) || e2Type.equals(TreeConstants.Bool) ||
            e1Type.equals(TreeConstants.Str) || e2Type.equals(TreeConstants.Str))
            if (!e1Type.equals(e2Type))
                classTable.reportError(this, classTable.ERROR_ARG_TYPE_MISMATCH);

        /* Return bool, regardless of error or not */
        type = TreeConstants.Bool;
        return type;
    }
}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);
        AbstractSymbol e2Type = e2.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Int)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e1Type));
        if (!e2Type.equals(TreeConstants.Int))
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Int, e2Type));
        /* Return bool, regardless of error or not */
        type = TreeConstants.Bool;
        return type;
    }
}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol e1Type = e1.computeType(classTable);

        if (!e1Type.equals(TreeConstants.Bool)) 
            classTable.reportError(this, String.format(classTable.ERROR_ARG_TYPE_MISMATCH,
                                   TreeConstants.Bool, e1Type));

        /* Return bool, regardless of error or not */
        type = TreeConstants.Bool;
        return type;
    }
}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        type = TreeConstants.Int;
        return type;
    }
}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        type = TreeConstants.Bool;
        return type; 
    }

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        type = TreeConstants.Str;
        return type;
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        type = type_name;
        if (classTable.isValidType(type_name))
            return type_name; 
        /* type not found */
        classTable.reportError(this, String.format(classTable.ERROR_TYPE_NOT_DEF, type_name));
        type = TreeConstants.No_type;
        return type;
    }

}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    
    public AbstractSymbol computeType(ClassTable classTable){
        AbstractSymbol expr_type = e1.computeType(classTable);
        type = TreeConstants.Bool;
        return type;
    }

}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        type = TreeConstants.No_type;
        return type;
    }
}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }

    public AbstractSymbol computeType(ClassTable classTable){
        /* Search in starting from inner most scope */
        if (classTable.localVarToType.lookup(name) != null){
            type = (AbstractSymbol) classTable.localVarToType.lookup(name);
            return type;
        }
        /* Search among class attributes */
        if (classTable.classToAttrMap.get(classTable.currClassName).containsKey(name)){
            type = (AbstractSymbol) classTable.classToAttrMap.get(classTable.currClassName).get(name);
            return type;
        }
        /* Object identifier not found */
        classTable.reportError(this, String.format(classTable.ERROR_VAR_NOT_DEFINED, name));
        type = TreeConstants.No_type;
        return type;
    }

}


