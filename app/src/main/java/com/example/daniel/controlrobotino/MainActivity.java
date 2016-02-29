package com.example.daniel.controlrobotino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button a,s,d,q,w,e,connect,disconnect;
    EditText portEditText;
    Socket socket;
    String portString;
    int portInt;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (Button) findViewById(R.id.a);
        s = (Button) findViewById(R.id.s);
        d = (Button) findViewById(R.id.d);
        q = (Button) findViewById(R.id.q);
        w = (Button) findViewById(R.id.w);
        e = (Button) findViewById(R.id.e);
        connect = (Button) findViewById(R.id.connect);
        disconnect = (Button) findViewById(R.id.off);
        portEditText = (EditText) findViewById(R.id.port);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disConnect();
            }
        });


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send("a");

            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("s");
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("d");
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send("w");

            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("q");
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("e");
            }
        });
    }

    private void disConnect() {



    }


    private void connect() {
        Log.d(TAG, "connect ");
        //  if (!connected) {
        Thread cThread = new Thread(new ClientThreadPy());
        cThread.start();
        Log.d(TAG, "start ");
        //    }
        //  }

    }

    private void send(String command) {
        Log.d(TAG, "send ");
        //  if (!connected) {
        try {

            OutputStream out = socket.getOutputStream();
            out.write(command.getBytes());
            out.flush();

        } catch (Exception e) {
            Log.e(TAG, "C: Error", e);
            //connected = false;
        }

        Log.d(TAG, "start ");
        //    }
        //  }

    }


    private class ClientThreadPy implements Runnable {
        //      boolean connected ;
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName("192.168.1.103");
                Log.d(TAG, "C: Connecting...");

                portString = portEditText.getText().toString();
                portInt = Integer.parseInt(portString);
                socket = new Socket(serverAddr, portInt);

                // connected = true;

     /*           DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                InputStream in = socket.getInputStream();

                out.writeChars("a");
                out.flush();
 */               Log.d(TAG, "C: flush.");

                // socket.close();
                Log.d(TAG, "C: Closed.");
            } catch (Exception e) {
                Log.e(TAG, "C: Error", e);
                //connected = false;
            }
        }
    }




}
