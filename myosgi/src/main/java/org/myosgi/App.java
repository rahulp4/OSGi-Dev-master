package org.myosgi;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellizonex.gateway.core.Storage;

/** In-memory Storage using a HashMap, enabled by default */
@Component
@Service(value=App.class)
public class App 
{
}
