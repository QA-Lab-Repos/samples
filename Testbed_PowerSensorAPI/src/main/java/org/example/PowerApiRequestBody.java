package org.example;

/*
JSON format

{
  "electricity": true,
  "gas": true,
  "span_start": "25/10/2022 12:00:00",
  "span_end": "26/10/2022 12:00:00"
}

 */

public class PowerApiRequestBody {
    private boolean Electricity;
    private boolean Gas;
    private String TimeSpanStart;
    private String TimeSpanEnd;
}
