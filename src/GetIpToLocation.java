/**
 * Created by odyssefs on 04.09.16.
 */

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;


public class GetIpToLocation {

    public static void main(String[] args) {
        GetIpToLocation obj = new GetIpToLocation();
        ServerLocation location = obj.getLocation("77.47.53.237");
        System.out.println(location);
    }

    public ServerLocation getLocation(String ipAddress) {

        File file = new File("./GeoLiteCity.dat");
        return getLocation(ipAddress, file);

    }

    public ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation location = null;

        try {

            location = new ServerLocation();

            LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
            Location lookupLocation = lookup.getLocation(ipAddress);

            location.setCountryCode(lookupLocation.countryCode);
            location.setCountryName(lookupLocation.countryName);
            location.setRegion(lookupLocation.region);
            location.setRegionName(regionName.regionNameByCode(lookupLocation.countryCode, lookupLocation.region));
            location.setCity(lookupLocation.city);
            location.setPostalCode(lookupLocation.postalCode);
            location.setLatitude(String.valueOf(lookupLocation.latitude));
            location.setLongitude(String.valueOf(lookupLocation.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return location;

    }
}
