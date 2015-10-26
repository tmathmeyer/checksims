package net.lldp.checksims.algorithm.syntaxtree;

import org.apache.commons.lang3.tuple.Pair;

import net.lldp.checksims.algorithm.AlgorithmResults;
import net.lldp.checksims.algorithm.InternalAlgorithmError;
import net.lldp.checksims.algorithm.SimilarityDetector;
import net.lldp.checksims.parse.Real;
import net.lldp.checksims.parse.SubmissionPercentableCalculator;
import net.lldp.checksims.parse.ast.AST;
import net.lldp.checksims.parse.ast.SubmissionParser;
import net.lldp.checksims.parse.token.TokenTypeMismatchException;
import net.lldp.checksims.submission.Submission;

public class JavaParser implements SimilarityDetector<AST>
{
    public static JavaParser getInstance()
    {
        return new JavaParser(); // TODO: make a singleton later?
    }
    
    @Override
    public String getName()
    {
        return "javaparser";
    }

    @Override
    public SubmissionPercentableCalculator<AST> getPercentableCalculator()
    {
        return new SubmissionParser(new JavaSyntaxParser());
    }

    @Override
    public AlgorithmResults detectSimilarity(Pair<Submission, Submission> ab, AST rft, AST comt)
            throws TokenTypeMismatchException, InternalAlgorithmError
    {
        Real atb = rft.getPercentMatched(comt.getFingerprints());
        Real bta = comt.getPercentMatched(rft.getFingerprints());
        
        return new AlgorithmResults(ab, atb, bta);
    }

}