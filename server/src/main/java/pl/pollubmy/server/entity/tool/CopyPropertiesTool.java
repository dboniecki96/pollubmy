package pl.pollubmy.server.entity.tool;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class CopyPropertiesTool {

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper beanWrapperSource = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] propertyDescriptors = beanWrapperSource.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor singleProperty : propertyDescriptors) {
            Object srcValue = beanWrapperSource.getPropertyValue(singleProperty.getName());
            if (srcValue == null) emptyNames.add(singleProperty.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
