package pojos.HerOkuAppPojo;

import pojos.HerOkuAppPojo.BookingPojo;

public class HerOkuAppResponsePojo {
        private Integer bookingid;
        private BookingPojo booking;

    public HerOkuAppResponsePojo() {
    }

    public HerOkuAppResponsePojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerOkuAppResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
