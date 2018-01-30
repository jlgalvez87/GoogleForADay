package com.google.fora.day.component.core;

import com.google.fora.day.exception.MalformedException;
import com.google.fora.day.exception.OpenUrlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

@Component
public final class URLContentReader {

    private String hostname = "localhost";
    private int port = 3129;
    private boolean using_proxy = true;

    @Value("${app.proxy.use_proxy}")
    public void setUsing_proxy(boolean using_proxy) {
        this.using_proxy = using_proxy;
    }

    @Value("${app.proxy.port}")
    public void setPort(int port) {
        this.port = port;
    }

    @Value("${app.proxy.hostname}")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHtmlContent(String urlString) throws MalformedException, OpenUrlException {

        if (urlString != null && !urlString.trim().isEmpty()) {

            URL url;

            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                throw new MalformedException();
            }

            URLConnection urlConnection;

            try {
                if (using_proxy) {

                    urlConnection = url.openConnection(new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress(hostname, port)));

                } else {
                    urlConnection = url.openConnection();
                }
            } catch (IOException e) {
                throw new OpenUrlException();
            }

            InputStream is;
            try {
                is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder content = new StringBuilder();

                String read;
                while ((read = br.readLine()) != null) {
                    content.append(read);
                }

                return content.toString().replaceAll("(\\s+|\n)", " ");
                //return content.toString();

            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }
}
