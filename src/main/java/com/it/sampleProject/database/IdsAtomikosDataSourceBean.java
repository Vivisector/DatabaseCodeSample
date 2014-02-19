/**
 * **************************************************************
 *
 * Copyright IntellectTechnologies LLC. All rights reserved.
 *
 * This software and its associated documentation contain proprietary,
 * confidential and trade secret information of IntellectTechnlogies LLC and
 * except as provided by written agreement with IntellectTechnologies LLC.
 *
 * a) no part may be disclosed, distributed, reproduced, transmitted,
 * transcribed, stored in a retrieval system, adapted or translated in any form
 * or by any means electronic, mechanical, magnetic, optical, chemical, manual
 * or otherwise, and b) the recipient is not entitled to discover through
 * reverse engineering or reverse compiling or other such techniques or
 * processes the trade secrets contained in the software code or to use any such
 * trade secrets contained therein or in the documentation.
 *
 **************************************************************
 */
package com.it.sampleProject.database;

import java.util.Enumeration;
import java.util.Properties;

import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 *
 * @author Igor Ivaniuk
 */
@SuppressWarnings("rawtypes")
public class IdsAtomikosDataSourceBean extends AtomikosDataSourceBean {

    private static final long serialVersionUID = -1500261813542306296L;
    Properties filteredProps = new Properties();
    @Override
    public void setXaProperties(Properties xaProperties) {
        
        Enumeration e = xaProperties.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (xaProperties.getProperty(key) != null && !xaProperties.getProperty(key).equals("") && !xaProperties.getProperty(key).contains("${")) {
                filteredProps.put(key, xaProperties.getProperty(key));
            }
        }
        super.setXaProperties(filteredProps);
    }
}
