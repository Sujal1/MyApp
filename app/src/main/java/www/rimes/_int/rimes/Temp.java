package www.rimes._int.rimes;

/**
 * Created by seasonal on 1/3/2017.
 */

public class Temp {


 /*Spinner mspinner_crop_param = (Spinner) view.findViewById(R.id.spinner_crop_param);


        List<String> categories_crop_parameter = new ArrayList<String>();
        categories_crop_parameter.add("Precipitation");
        categories_crop_parameter.add("Temperature");
        categories_crop_parameter.add("PET");

        ArrayAdapter<String> adapter_crop_parameter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_crop_parameter);

        adapter_crop_parameter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mspinner_crop_param.setAdapter(adapter_crop_parameter);*/


       /* myCalendar = Calendar.getInstance();

        Button loadDekad = (Button) view.findViewById(R.id.loadDekad);
        loadDekad.setOnClickListener(TabFragment3.this);


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        editTextFrom = (EditText) view.findViewById(R.id.editTextFrom);
        editTextTo = (EditText) view.findViewById(R.id.editTextTo);

        editTextFrom.setOnClickListener(TabFragment3.this);
        editTextTo.setOnClickListener(TabFragment3.this);

    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.loadDekad) {

            Toast.makeText(getActivity(), "Loading", Toast.LENGTH_LONG).show();

        }

        else if (v.getId() == R.id.editTextTo) {

            from = false;

            new DatePickerDialog(getActivity(),
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show();

        }

        else if (v.getId() == R.id.editTextFrom) {

            from = true;

            new DatePickerDialog(getActivity(),
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show();

        }




    }


    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (from) {
            editTextFrom.setText(sdf.format(myCalendar.getTime()));
        } else {
            editTextTo.setText(sdf.format(myCalendar.getTime()));
        }

    }*/

}