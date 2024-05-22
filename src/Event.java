import java.sql.*;

public class Event extends OracleConnector{
    private int id;
    private String eventName;
    private int typeId;
    private int venueId;
    private  int guests;
    private String eventDate;
    private String startTime;
    private String endTime;
    private int customerId;
    private double eventCost;

    public Event(String databaseName, String password){
        super(databaseName, password);
    }

    public int getGuests() {
        return guests;
    }
    public void setGuests(int guests){
        this.guests = guests;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public double getEventCost() {
        return eventCost;
    }

    public void setEventCost(double eventCost) {
        this.eventCost = eventCost;
    }

    /**
     * this method gets last event id and assigns the new event id adding 1
     */
    public void setId(){
        int event_id = 0;
        try {
                   ResultSet eventResult = giveQuery("SELECT id from event ORDER BY id");
        if (!eventResult.next()) {
            event_id = 1;
        } else {
            event_id = eventResult.getInt(1);
            while (eventResult.next()) {
                event_id = eventResult.getInt(1);
            }
        }
            System.out.println(event_id);
            eventResult.close();
        } catch (SQLException e1){
            e1.printStackTrace();
        }
        id = event_id + 1;
    }

    public void addEvent() {

        try {
            // Establish connection to the database
            connect();
            // Prepare the call to the stored procedure
            cstmt = conn.prepareCall("{call add_event(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            // Set the input parameters
            cstmt.setString(1, eventName);
            cstmt.setInt(2, typeId);
            cstmt.setInt(3, venueId);
            cstmt.setDate(4, Date.valueOf(eventDate)); // Convert eventDate to SQL Date
            cstmt.setString(5, startTime);
            cstmt.setString(6, endTime);
            cstmt.setInt(7, customerId);
            cstmt.setInt(8, guests);
            cstmt.setDouble(9, eventCost);
            cstmt.setInt(10, id);

            // Execute the stored procedure
            cstmt.execute();

            System.out.println("Record added to Event table.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the statement and connection
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void addEvent() {
//
//        String sql = "INSERT INTO Event (event_name, type_id, venue_id, event_date, start_time," +
//                " end_time, customer_id, guest, event_cost, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try {
//            connect();
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, eventName);
//            pst.setInt(2, typeId);
//            pst.setInt(3, venueId);
//            pst.setDate(4, Date.valueOf(eventDate));//"yyyy-MM-dd" format String
//            pst.setString(5, startTime);
//            pst.setString(6, endTime);
//            pst.setInt(7, customerId);
//            pst.setInt(8, guests);
//            pst.setDouble(9, eventCost);
//            pst.setInt(10, id);
//
//            pst.executeUpdate();
//            System.out.println("Record added to Event table.");
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
