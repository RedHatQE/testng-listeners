package com.redhat.qe.auto.testng;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;


/**
 * this listener is processing {@link SkipIf} annotations on method / class level
 * and marks tests as SKIPPED if required
 * @author lzoubek@redhat.com
 *
 */
public class SkipTestNGListener implements ITestListener {
    
    protected static Logger log = Logger.getLogger(SkipTestNGListener.class.getName());
    
    @Override
    public void onTestStart(ITestResult result) {
	SkipIf skip = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(SkipIf.class);
	if (skip==null) {
	    // let's lookup on class level
	    skip = getClassAnnotation(result.getTestClass().getRealClass());
	}
	if (skip!=null) {
	    if (!isDefault(skip.property())) {
		String property = System.getProperty(skip.property());
		if (property == null) {
		    // we do not care when such property is not defined
		    return;
		}
		if (!isDefault(skip.equals())) {
		    if (skip.equals().equals(property)) {
			throw new SkipException("Skipped because property ["+skip.property()+"="+property+"] equals ["+skip.equals()+"]");
		    }
		}
		if (!isDefault(skip.notEquals())) {
		    if (!skip.notEquals().equals(property)) {
			throw new SkipException("Skipped because property ["+skip.property()+"="+property+"] does not equal ["+skip.notEquals()+"]");
		    }
		}
		if (!isDefault(skip.contains())) {
		    if (property.contains(skip.contains())) {
			throw new SkipException("Skipped because property ["+skip.property()+"="+property+"] contains ["+skip.contains()+"]");
		    }
		}
	    }
 	}
    }
    
    /**
     * finds {@link SkipIf} annotation in given class or recursive in super
     * classes
     * 
     * @param klass
     * @return
     */
    private SkipIf getClassAnnotation(Class<?> klass) {
	if (klass == null || Object.class.equals(klass)) {
	    return null;
	}
	SkipIf skip = klass.getAnnotation(SkipIf.class);
	if (skip != null) {
	    return skip;
	}
	return getClassAnnotation(klass.getSuperclass());
    }
    
    private boolean isDefault(String value) {	
	return "".equals(value);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
	
    }

    @Override
    public void onTestFailure(ITestResult result) {
	
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
	
    }

}
