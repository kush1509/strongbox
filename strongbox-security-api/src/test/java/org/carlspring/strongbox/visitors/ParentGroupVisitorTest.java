package org.carlspring.strongbox.visitors;

import org.carlspring.strongbox.security.jaas.Group;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author mtodorov
 */
public class ParentGroupVisitorTest
{

    @Test
    public void testVisitor()
    {
        Group group1 = createGroup("employees", "Employees", null);
        Group group2 = createGroup("developers", "Developers", group1);
        Group group3 = createGroup("java-developers-uk", "Java Developers UK", group2);

        List<Group> nestedGroups = new ArrayList<Group>();
        ParentGroupVisitor visitor = new ParentGroupVisitor();
        visitor.visit(group3, nestedGroups);

        for (Group group : nestedGroups)
        {
            System.out.println(group.getName());
        }
    }

    private Group createGroup(final String name, final String description, final Group parent)
    {
        //noinspection UnnecessaryLocalVariable
        Group group = new Group()
        {

            @Override
            public String getName()
            {
                return name;
            }

            @Override
            public String getDescription()
            {
                // We don't care.
                return description;
            }

            @Override
            public Group getParent()
            {
                return parent;
            }

        };

        return group;
    }

}