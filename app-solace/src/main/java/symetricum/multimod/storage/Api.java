package symetricum.multimod.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Api {
    final StorageManagerService storageManagerService;
    final StoragesProps storagesProps;

    @GetMapping("/api/storages")
    public StoragesProps getStorages() {
        val props = new StoragesProps();
        props.active = storageManagerService.getActiveStorage();
        props.configuration = storagesProps.getConfiguration();
        return props;
    }

    @PostMapping("/api/storages")
    @ResponseStatus(HttpStatus.OK)
    public StoragesProps postSetActive(@RequestParam String active) {
        storageManagerService.setActiveStorage(StoragesConfig.Types.valueOf(active));
        return getStorages();
    }

}
