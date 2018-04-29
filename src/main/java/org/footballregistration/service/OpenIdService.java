package org.footballregistration.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.footballregistration.common.Constants;
import org.footballregistration.response.CommonResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class OpenIdService {

	public String getOpenId(String code) {

		CommonResponse response = new CommonResponse();


		try {

		StringBuffer result = new StringBuffer();

		StringBuilder urlPath = new StringBuilder(Constants.OPENID_URL);
		urlPath.append(String.format("?appid=%s", Constants.APPID));
		urlPath.append(String.format("&secret=%s", Constants.SECRET));
		urlPath.append(String.format("&js_code=%s", code));
		urlPath.append(String.format("&grant_type=%s", "authorization_code")); // 固定值

		URL url = new URL(urlPath.toString());
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");

		con.connect();
		final int status = con.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            final InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            if(null == encoding){
                encoding = "UTF-8";
            }
            final InputStreamReader inReader = new InputStreamReader(in, encoding);
            final BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            while((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            bufReader.close();
            inReader.close();
            in.close();
        } else {
            System.out.println(status);
        }

			System.out.println("result：" + result);
			response.responseCode = Constants.RESPONSE_CODE_OK;
		} catch (Exception e) {
			e.printStackTrace();
			response.responseCode = Constants.RESPONSE_CODE_NG;
		}

		Gson gson = new Gson();

		return gson.toJson(response);
	}

}
