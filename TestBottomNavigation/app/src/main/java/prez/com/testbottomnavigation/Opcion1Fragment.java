package prez.com.testbottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Opcion1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_opcion1, container, false);
        final Context c = v.getContext();

        Button btnGuardar = (Button) v.findViewById(R.id.btnOp1);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "Botón click!", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
}
